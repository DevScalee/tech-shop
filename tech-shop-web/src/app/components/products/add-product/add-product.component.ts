import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { CategoryService } from 'src/app/services/category.service';
import { ProductService } from 'src/app/services/product.service';
@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css'],
})
export class AddProductComponent implements OnInit {
  inc = 0;
  product: any = {};
  imagePreview: string = '';
  imagePrevieww: string = '';
  msg: any;
  visible: any;
  ok: any;
  f: any;
  ff: any;
  categoryForm: FormGroup = new FormGroup({
    name: new FormControl(''),
    categ: new FormControl(''),
  });
  category: any = {};
  productForm: FormGroup = new FormGroup({
    name: new FormControl(''),
    prix: new FormControl(''),
    categorie: new FormControl(''),
    quantityInStock: new FormControl(''),
    description: new FormControl(''),
    images: new FormControl(''),
    //  imgg: new FormControl(''),
    // solde: new FormControl(''),
    // remise: new FormControl(''),
    //  detail: new FormControl('')
    // new_price : new FormControl('')
  });
  cat: any;
  number_category: any;

  imageFile : any ;
  constructor(
    private productService: ProductService,
    private formBuilder: FormBuilder,
    private router: Router,
    private fb: FormBuilder,
    private categoryService: CategoryService
  ) {}

  ngOnInit(): void {
    this.categoryForm = this.fb.group({
      name: [''],
      categ: [''],
    });

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
      // new_price : ['']
    });

    this.getAllCategory();
  }

  Addproduct() {
    const categObj = this.category.categ;
    this.product.categorie = categObj;

    const v = this.checkData(this.product);

    const formValues = this.product;

  

    const formData :FormData = new FormData()
    formData.set('product', new Blob([JSON.stringify(formValues)],{type :'application/json'}))
    formData.append("images",this.imageFile)


    
    console.log('this is product', this.imageFile);
    if (v == true) {
      this.productService
        .addProduct(formData)
        .subscribe((data) => {
          console.log('Here data from BE after add product', data);
        });
    } else {
      console.log('error');
    }
  }

  addCategory() {
    console.log('Add Category :', this.category.name);
    this.categoryService.AddCategory(this.category).subscribe((data) => {
      console.log(data.message);
      this.ngOnInit();
    });
  }

  getAllCategory() {
    console.log('get all categorys');
    this.categoryService.allCategory().subscribe((data) => {
      this.cat = data;
      //this.number_category = data.nbr;
    });
  }

  counter(i: number) {
    return new Array(i);
  }
  checkData(product: any) {
    var valide = true;
    console.log(valide);
    if (product.name == undefined) {
      this.msg = 'Name is required !';
      valide = false;
      this.ok = valide;
    } else if (product.prix == undefined) {
      this.msg = 'Price is required !';
      valide = false;
      this.ok = false;
    } 
    else if (product.quantityInStock == undefined) {
      this.msg = 'Quantity is required !';
      valide = false;
      this.ok = valide;
    } else if (product.description == undefined) {
      this.msg = 'Description is required !';
      valide = false;
      this.ok = valide;
    } else if (this.f == '' || this.f == undefined) {
      this.msg = 'Photo 1 is required !';
      valide = false;
      this.ok = valide;
    } else {
      valide = true;
    }
    return valide;
  }

  onImageSelected(event: Event) {
    const file = (event.target as HTMLInputElement)?.files?.[0];
    this.f = file;
    this.productForm.patchValue({ img: file });
    this.productForm.updateValueAndValidity();
    const reader = new FileReader();
    reader.onload = () => {
      this.imagePreview = reader.result as string;
      this.imageFile = file;
      console.log('image 1', file);
    };
    if (file) {
      reader.readAsDataURL(file);
    }
  }
}
