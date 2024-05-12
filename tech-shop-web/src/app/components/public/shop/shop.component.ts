import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { CategoryService } from 'src/app/services/category.service';
import { PanierService } from 'src/app/services/panier.service';
import { ProductService } from 'src/app/services/product.service';
import { WhishlistService } from 'src/app/services/whishlist.service';


@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.css']
})
export class ShopComponent implements OnInit {

  category : any ;
  products : any ;
  selectedCategory : any ;
  categ : any ;
  id_user : any ;
  p: number = 1;

  productForm: FormGroup = new FormGroup({
    name: new FormControl(''),
    prix: new FormControl(''),
    categorie: new FormControl(''),
    quantityInStock: new FormControl(''),
    description: new FormControl(''),
    images: new FormControl('')
  });


  constructor(private productService : ProductService,
              private router : Router,
              private categoryService : CategoryService,
              private whishlistService : WhishlistService,
              private panierService : PanierService,
              private formBuilder : FormBuilder
              ) { }

  ngOnInit(): void {


    this.productForm = this.formBuilder.group({
      name: [''],
      prix: [''],
      categorie: [''],
      quantityInStock: [''],
      description: [''],
      images: [''],  
    })


    this.id_user = localStorage.getItem("connectedUser");
    this.GetCategory();
    this.GetProduct();
    
  }

  // GetProduct(){
  //   console.log('get all products');
  //   this.productService.GetProductInStock().subscribe(
  //     (data)=>{
  //       this.products = data.products;
  //       console.log('products',this.products);
  //     }
  //   )
  // }

 
  GetProduct(){
    
    this.productService.getAllproducts().subscribe({
      next: (data) => {
        this.products = data;
        // .map(data => {
        //   // Check if the img property exists and has at least one element
        //   if (product.img && product.img.length > 0) {
        //     // Assign the first image's Base64 string as a data URL
        //     product.imageSrc = `data:image/jpeg;base64,${product.img[0]}`;
        //   } else {
        //     // Optionally, assign a default image if no image is found
        //     product.imageSrc = 'path/to/default/image.jpg'; // Adjust the path as necessary
        //   }
        //   return product;
       // });
      },
      error: (err) => console.error('Error loading products:', err)
     
    });
  }

  GetCategory(){
    console.log('Get All Category');
    this.categoryService.allCategory().subscribe(
      (result)=>{
        this.category = result;
        console.log('category :',this.category);
      }
    )
  }

  SelectedCategory(ch : any){
    this.selectedCategory = ch ;
    console.log('this is category ',this.selectedCategory);
    this.GetSelectedCategory(ch);
  }

  SelectedPrice(ch : any){
    console.log('priice',ch);
    if (ch == 'low'){
      this.productService.LowerProduct(this.products).subscribe(
        (data)=>{
          this.products = data.product;
        }
      )
    }
    else if (ch == 'high'){
      this.productService.HighProduct(this.products).subscribe(
        (data)=>{
          this.products = data.product;
        }
      )
    }
  }

  GetSelectedCategory(category : any){
    console.log('d5aal',category);
    this.productService.getProductByCategory(category).subscribe(
      (data)=>{
        this.products = data.produit
        console.log('hellooww',this.products);
      }
    )
   
  }

  cleanAll(){
    this.ngOnInit();
  }

  viewProduct(id : any ){
    console.log('Show Details of products :');
    this.router.navigate([`view-product/${id}`]).then(
      ()=>{
        this.ngOnInit();
        window.location.reload();
      }
    )
  }

  addWishlist(produit : any ) {
    produit.user = this.id_user;
    console.log('Adding this product in Whislist :', produit);
    this.whishlistService.AddProduct(produit).subscribe(
      (data) => {
        console.log(data.message);
       // this.ngOnInit();
      }
    )
  }

  SaveInPanier(produit : any ) {
    produit.stock = "In stock";
      produit.stock = "In stock" ;
      produit.user = this.id_user ;
     console.log('bbbbb',produit);
      this.panierService.AddPanier(produit).subscribe(
      (data) => {
      console.log('Here data from BE after add product in panier',data);
      }
      )
  }








}
