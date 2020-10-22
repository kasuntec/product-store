import { Component, OnInit } from '@angular/core';
import {ItemService} from '../service/item.service';
import {Item} from '../model/item';
import {PriceList} from '../model/price-list';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ShoppingCart} from '../model/shopping-cart';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {

  public itemForm: FormGroup;

  constructor(private itemService: ItemService) {
  }

   items: Item[];

  actualPrice: any;
  lineAmount: any;
  cart: ShoppingCart[] = [];
  cartAmount: any;



  ngOnInit(): void {
    this.cartAmount = 0.0;
    this.lineAmount = 0.0;

    // load items list
    this.findAllItems();

   this.initPageValues();
  }


  initPageValues() {

    // init form
    this.itemForm = new FormGroup({
      id: new FormControl(''),
      itemId: new FormControl('', Validators.required),
      unitType: new FormControl('', Validators.required),
      qty: new FormControl('0', Validators.required)
    });

    // init some values
    this.actualPrice = 'N/A';
  }

  findAllItems() {
    this.itemService.findAll()
      .subscribe((res: Item[]) => {
        this.items = res;
      });
  }

  calculateActualPrice() {
    const itemId =  this.itemForm.value.itemId;
    const qty =  +this.itemForm.value.qty;
    const unitType =  this.itemForm.value.unitType;

    if (itemId !== '' && qty !== 0 && unitType !== '' ) {
      this.actualPrice = 'Calculating..';
      this.itemService.calculateActualPrice(itemId, unitType, qty)
        .subscribe((res: any) => {
          this.actualPrice = res.actualPrice;
          const totalQty = res.totalQty;

          // set line amount
          this.lineAmount = this.actualPrice * totalQty;
        }, (error: Error) => {
          console.info('Error calculating price');
        }, () => {});
    }


  }

  addToCart() {
    const shopingCatItem: ShoppingCart = this.itemForm.value;
    shopingCatItem.description
      = this.items.find(obj => (obj.id = this.itemForm.value.itemId)).description;
    shopingCatItem.unitPrice = this.actualPrice;
    shopingCatItem.lineAmount = this.lineAmount;
    this.cart.push(shopingCatItem);

    this.calculateCartTotal();
    this.initPageValues();
  }

  calculateCartTotal () {
    for (let item of this.cart) {
      this.cartAmount = this.cartAmount + item.lineAmount;
    }
  }




}
