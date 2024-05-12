import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PanierService } from 'src/app/services/panier.service';
import { ProductService } from 'src/app/services/product.service';
import { UserService } from 'src/app/services/user.service';
import { WhishlistService } from 'src/app/services/whishlist.service';

@Component({
  selector: 'app-home-shop',
  templateUrl: './home-shop.component.html',
  styleUrls: ['./home-shop.component.css']
})
export class HomeShopComponent implements OnInit {
  product : any ;
  id_user : any ;
  user: any ;
  constructor(private productService : ProductService,
              private router : Router,
              private wishlistService : WhishlistService,
              private panierService : PanierService,
            private userService : UserService) { }

  ngOnInit(): void {
    this.id_user = localStorage.getItem("connectedUser");
    this.getUser(this.id_user)
    this.GetProducts();
  }

  getUser(id : any){
   
    this.userService.getUserById(id).subscribe(
      (data)=>{
        this.user = data;
        console.log('Here In Get User In Profil',this.user);
      }
    )

  }

  GetProducts(){
    this.productService.HomeProducts().subscribe(
      (data)=>{
        this.product = data;
        console.log('',this.product);
      }
    )
  }

  addWishlist(produit : any ) {
    produit.user = this.id_user;
    console.log('Adding this product in Whislist :', produit);
    // this.wishlistService.AddProduct(produit).subscribe(
    //   (data) => {
    //     console.log(data.message);
    //   }
    // )
  }

  SaveInPanier(produit : any ) {
    produit.user = this.id_user ;
    produit.cart_id = this.user.cart.id;
    produit.quantity = 1 ;
    console.log('add panier', produit);
    this.panierService.AddPanier(produit).subscribe(
    (data) => {
    console.log('Here data from BE after add product in panier',data);
      }
    )
  }

  viewProduct(id : any ){
    console.log('Show Details of products :');
    this.router.navigate([`view-product/${id}`]).then
    // (
    //   ()=>{
    //     this.ngOnInit();
    //     window.location.reload();
    //   }
    // )
  }




}
