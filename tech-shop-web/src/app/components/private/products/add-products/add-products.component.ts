import { Component } from '@angular/core';
import { HeaderComponent } from '../../../public/header/header.component';

@Component({
  selector: 'app-add-products',
  standalone: true,
  imports: [HeaderComponent],
  templateUrl: './add-products.component.html',
  styleUrl: './add-products.component.scss',
})
export class AddProductsComponent {}
