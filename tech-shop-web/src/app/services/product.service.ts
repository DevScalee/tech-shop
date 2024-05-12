import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  productUrl: string = 'http://localhost:9050';

  constructor(private httpClient: HttpClient) {}


addProduct(formData:FormData):Observable<Object> {
  return this.httpClient.post( `${this.productUrl}/api/addproduct`,formData)
}





  editProduct1(product: any, img: File) {
    let formData = new FormData();
    formData.append('name', product.name);
    formData.append('price', product.price);
    formData.append('categorie', product.categorie);
    formData.append('quantity', product.quantity);
    formData.append('description', product.description);
    formData.append('detail', product.detail);
    formData.append('img', img);
    formData.append('new_price', product.new_price);
    formData.append('solde', product.solde);
    formData.append('remise', product.remise);
    return this.httpClient.put<{ message: String; product: any }>(
      `${this.productUrl}/api/products/edit1/${product._id}`,
      formData
    );
  }

  editProduct(formData : FormData, id : any){
    return this.httpClient.put<{ product:any}>(
      `${this.productUrl}/api/updateproduct/${id}`,
      formData
    );
  }
 

  getAllproducts() {
    return this.httpClient.get(
      `${this.productUrl}/api/products`
    );
  }

  GetProductInStock() {
    return this.httpClient.get<{ products: any }>(
      `${this.productUrl}/api/products/instock`
    );
  }

  gettrends() {
    return this.httpClient.get<{ trend: any }>(
      `${this.productUrl}/api/products/trends`
    );
  }

  getProductById(id: any) {
    return this.httpClient.get<{ product: any }>(
      `${this.productUrl}/api/findproduct/${id}`
    );
  }

  getProductByName(name: any) {
    return this.httpClient.get<{ product: any }>(
      `${this.productUrl}/api/products/product/${name}`
    );
  }

  deleteProduct(id: any) {
    return this.httpClient.delete<{ message: string }>(
      `${this.productUrl}/api/deleteproduct/${id}`
    );
  }

  

  searchByName(trainerObj: any) {
    return this.httpClient.post<{ findedProduct: any; message: string }>(
      `${this.productUrl}/api/product/search/trainerName`,
      trainerObj
    );
  }
  getProductByCategory(id: any) {
    return this.httpClient.get<{ produit: any }>(
      `${this.productUrl}/api/products/category/${id}`
    );
  }

  DeleteQuantity(product: any) {
    return this.httpClient.post<{ message: any }>(
      `${this.productUrl}/api/products/delete/quantity`,
      product
    );
  }

  LatestArrivals() {
    return this.httpClient.get<{ produits: any }>(
      `${this.productUrl}/api/products/latest`
    );
  }

  LowerProduct(product: any) {
    return this.httpClient.post<{ product: any }>(
      `${this.productUrl}/api/products/lower`,
      product
    );
  }

  HighProduct(product: any) {
    return this.httpClient.post<{ product: any }>(
      `${this.productUrl}/api/products/high`,
      product
    );
  }
 
  HomeProducts() {
    return this.httpClient.get(
      `${this.productUrl}/api/product/latest`
    );
  }
}
