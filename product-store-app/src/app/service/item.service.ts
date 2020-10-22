import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  constructor(private http: HttpClient) { }

  apiUrl = environment.api_url + 'item/';

  findAll() {
    return this.http.get(this.apiUrl);
  }

  listAllPriceByItem(itemId: number, fromQty: number, toQty: number) {
    return this.http.get(this.apiUrl + '/price-list/item/' + itemId + '/' + fromQty + '/' + toQty);
  }

  calculateActualPrice(itemId: number, unitType: string, qty: number) {
    return this.http.get(this.apiUrl + '/actual-price/' + itemId + '/' + unitType + '/' + qty);
  }
}
