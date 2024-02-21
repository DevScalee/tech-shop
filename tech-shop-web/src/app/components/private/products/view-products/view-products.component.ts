import { Component } from '@angular/core';
import { AdminHeaderComponent } from '../../admin-header/admin-header.component';

@Component({
  selector: 'app-view-products',
  standalone: true,
  imports: [AdminHeaderComponent],
  templateUrl: './view-products.component.html',
  styleUrl: './view-products.component.scss',
})
export class ViewProductsComponent {}
