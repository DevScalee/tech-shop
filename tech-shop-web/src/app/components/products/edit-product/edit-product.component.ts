import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { CategoryService } from 'src/app/services/category.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.css']
})
export class EditProductComponent implements OnInit {
  product: any = {};
  inc : any ;
  imagePreview: string = '';
  imagePrevieww: string = '';
  msg: any;
  number_category: any;
  cat : any ;
  f: any;
  ff: any;
  ok : any ;
  id : any ;
  image : any ;
  productForm: FormGroup = new FormGroup({
    name: new FormControl(''),
    prix: new FormControl(''),
    categorie: new FormControl(''),
    quantityInStock: new FormControl(''),
    description: new FormControl(''),
    images: new FormControl(''),
    // imgg: new FormControl(''),
    // solde: new FormControl(''),
    // remise: new FormControl(''),
    // detail: new FormControl('')
    // new_price : new FormControl('')
  });
  constructor(private categoryService : CategoryService,
              private formBuilder : FormBuilder,
              private productService : ProductService,
              private activatedRoute : ActivatedRoute,
              private router : Router) { }

  ngOnInit(): void {

    this.productForm = this.formBuilder.group({
      name: [''],
      prix: [''],
      categorie: [''],
      quantityInStock: [''],
      description: [''],
      images: [''],
      // imgg: [''],
      // solde: [''],
      // remise: [''],
      // detail: ['']
     
    })

    this.id = this.activatedRoute.snapshot.paramMap.get('id');
    this.Getproduct(this.id);
    this.image = this.product.img ;
    this.getAllCategory();
  }

  getAllCategory(){
    console.log('get all categorys');
    this.categoryService.allCategory().subscribe(
      (data)=>{
        this.cat = data;
     
      }
    )
  }

  imageFile : any ;
  Getproduct(id : any){
    this.productService.getProductById(id).subscribe(
      (data)=>{
        this.product = data ;
        if (this.product.img && this.product.img.length > 0) {
          this.product.imageSrc = `data:image/jpeg;base64,${this.product.img[0]}`;
        }


        console.log(this.product);
      }
    )
  }


  // EditProduct(){

    
  //   const formData :FormData = new FormData()
  //   formData.set('product', new Blob([JSON.stringify(formValues)],{type :'application/json'}))
  //   formData.append("images",this.imageFile)
  // }
  EditProduct(){
    console.log('lll',this.imagePreview != "");
   
    var photo1 = (this.productForm.value.images !="" );
   
  const v = this.checkData(this.product);
  if ( v == false){
    console.log('error');
  }
  else {
    if( photo1 ==  false ){
       
        this.Edit();
         this.router.navigate([`products`]);
       //  window.setTimeout(function(){location.reload()},2000)
        // this.router.navigate([`products`]).then(() => {
        
        //   window.location.reload();
        // });
    }


  }
  }

   Edit(){

    const formValues = this.product;
    const formData :FormData = new FormData()
    formData.set('product', new Blob([JSON.stringify(formValues)],{type :'application/json'}))
    formData.append("images",this.imageFile)

    console.log('this product',this.product)
   this.productService.editProduct(formData, this.product.id).subscribe(
    (data:any)=>{
      this.router.navigate(['/products']).then(() => {
        window.location.reload();
      });

      console.log('helloolelelelel')
      console.log(data);
    }
  )
  }

  
  checkData(product: any) {
    var valide = true;
    if (product.name == undefined) {
      this.msg = "Name is required !";
      valide = false;
      this.ok = valide;
    }
    else if (product.prix == undefined) {
      this.msg = "Price is required !";
      valide = false;
      this.ok = false;
    }
    else if (product.quantityInStock == undefined) {
      this.msg = "Quantity is required !";
      valide = false;
      this.ok = valide;
    }
    else if (product.description == undefined) {
      this.msg = "Description is required !";
      valide = false;
      this.ok = valide;
    }
    else {
      valide = true;
    }
    return valide;
  }




  radio() {
    console.log('hi !');
    this.inc = this.inc + 1;
    console.log(this.inc);
    if (this.inc % 2 == 0) {
      console.log('paire');
      this.product.solde = true;
    }
    else {
      this.product.solde = false;
      this.product.remise = 0;
    }
  }


  onImageSelected(event: Event) {
    const file = (event.target as HTMLInputElement)?.files?.[0];
    this.f = file;
    this.productForm.patchValue({ img: file });
    this.productForm.updateValueAndValidity();
    const reader = new FileReader();
    reader.onload = () => {
      this.imagePreview = reader.result as string
      this.imageFile = file;
      console.log('image 1', file);
    };
    if (file) {
      reader.readAsDataURL(file);
    }
  }
 
}
