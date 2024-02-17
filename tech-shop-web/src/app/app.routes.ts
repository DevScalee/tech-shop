import { Routes } from '@angular/router';
import { HomeComponent } from './components/public/home/home.component';
import { FooterComponent } from './components/public/footer/footer.component';
import { HeaderComponent } from './components/public/header/header.component';
import { AccountComponent } from './components/public/account/account.component';
import { LoginComponent } from './components/public/login/login.component';
import { SignUpComponent } from './components/public/sign-up/sign-up.component';
import { AddProductsComponent } from './components/private/products/add-products/add-products.component';

export const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    title: 'Home page',
  },
  {
    path: 'footer',
    component: FooterComponent,
    title: 'footer',
  },
  {
    path: 'header',
    component: HeaderComponent,
    title: 'header',
  },
  {
    path: 'account',
    component: AccountComponent,
    title: 'account',
  },
  {
    path: 'login',
    component: LoginComponent,
    title: 'login',
  },
  {
    path: 'signup',
    component: SignUpComponent,
    title: 'signup',
  },
  {
    path: 'add-product',
    component: AddProductsComponent,
    title: 'add-product',
  },
];
