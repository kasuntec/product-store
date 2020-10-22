import {Component, OnInit} from '@angular/core';
import {ItemService} from '../service/item.service';
import {Item} from '../model/item';
import {PriceList} from '../model/price-list';

@Component({
  selector: 'app-product-prices',
  templateUrl: './product-prices.component.html',
  styleUrls: ['./product-prices.component.css']
})
export class ProductPricesComponent implements OnInit {

  constructor(private itemService: ItemService) {
  }

  fromQty = 1;
  toQty = 50;

  items: Item[];
  priceList: PriceList[];

  selectedValue: number;

  ngOnInit(): void {
    this.selectedValue = 0;
    this.findAllItems();
  }

  findAllItems() {
    this.itemService.findAll()
      .subscribe((res: Item[]) => {
        this.items = res;
      });
  }

  listAllPriceByItem(itemId: any) {
    if (itemId !== '0') {
      this.itemService.listAllPriceByItem(itemId, this.fromQty, this.toQty)
        .subscribe((res: PriceList[]) => {
          this.priceList = res;
        });
    }
    else {
      this.priceList = [];
    }
  }

}
