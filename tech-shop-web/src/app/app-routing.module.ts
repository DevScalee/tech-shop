import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccountComponent } from './components/account/account.component';
import { AddBlogComponent } from './components/add-blog/add-blog.component';
import { BlogsComponent } from './components/admin/blogs/blogs.component';
import { CustomersComponent } from './components/admin/customers/customers.component';
import { EmployeeComponent } from './components/admin/employee/employee.component';
import { FeedbacksComponent } from './components/admin/feedbacks/feedbacks.component';
import { HistoryComponent } from './components/admin/history/history.component';
import { HomeAdminComponent } from './components/admin/home-admin/home-admin.component';
import { NavComponent } from './components/admin/nav/nav.component';
import { OrdersComponent } from './components/admin/orders/orders.component';
import { ProductsComponent } from './components/admin/products/products.component';
import { WishlistComponent } from './components/admin/wishlist/wishlist.component';
import { BlogComponent } from './components/blog/blog.component';
import { CartComponent } from './components/cart/cart.component';
import { EditBlogComponent } from './components/edit-blog/edit-blog.component';
import { EditUserComponent } from './components/edit-user/edit-user.component';
import { EBlogsComponent } from './components/employee/e-blogs/e-blogs.component';
import { ECustomersComponent } from './components/employee/e-customers/e-customers.component';
import { EFeedbacksComponent } from './components/employee/e-feedbacks/e-feedbacks.component';
import { ENavComponent } from './components/employee/e-nav/e-nav.component';
import { EOrdersComponent } from './components/employee/e-orders/e-orders.component';
import { EProductsComponent } from './components/employee/e-products/e-products.component';
import { EWishlistsComponent } from './components/employee/e-wishlists/e-wishlists.component';
import { ErrorComponent } from './components/error/error.component';
import { RelatedProductComponent } from './components/related-product/related-product.component';
import { ViewBlogComponent } from './components/view-blog/view-blog.component';
import { ViewBlogsComponent } from './components/view-blogs/view-blogs.component';
import { WhishlistComponent } from './components/whishlist/whishlist.component';
import { SwapComponent } from './components/swap/swap.component';
import { AddSwapComponent } from './components/swip_management/add-swap/add-swap.component';
import { ViewProductSwapComponent } from './components/swip_management/view-product-swap/view-product-swap.component';
import { ProfilComponent } from './components/public/profil/profil.component';
import { AddProductComponent } from './components/products/add-product/add-product.component';
import { ContactComponent } from './components/public/contact/contact.component';
import { ShopComponent } from './components/public/shop/shop.component';
import { ViewProductComponent } from './components/products/view-product/view-product.component';
import { PaymentComponent } from './components/private/payment/payment.component';
import { ResetPasswordComponent } from './components/private/reset-password/reset-password.component';
import { EditProductComponent } from './components/products/edit-product/edit-product.component';
import { HomeShopComponent } from './components/public/home-shop/home-shop.component';
import { HomeBlogComponent } from './components/public/home-blog/home-blog.component';
import { HomeComponent } from './components/public/home/home.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'account', component: AccountComponent },
  { path: 'cart', component: CartComponent },
  { path: 'profile/:id', component: ProfilComponent },
  { path: 'add-product', component: AddProductComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'edit-user/:id', component: EditUserComponent },
  { path: 'shop', component: ShopComponent },
  { path: 'whislist', component: WhishlistComponent },
  { path: 'blog', component: BlogComponent },
  { path: 'view-product/:id', component: ViewProductComponent },
  { path: 'view-product-swap/:id', component: ViewProductSwapComponent },
  { path: 'add-blog', component: AddBlogComponent },
  { path: 'view-blogs', component: ViewBlogsComponent },
  { path: 'view-blog/:id', component: ViewBlogComponent },
  { path: 'payment/:id', component: PaymentComponent },
  { path: 'reset-password/:token', component: ResetPasswordComponent },
  { path: 'error', component: ErrorComponent },
  { path: 'nav', component: NavComponent },
  { path: 'admin', component: HomeAdminComponent },
  { path: 'products', component: ProductsComponent },
  { path: 'edit-product/:id', component: EditProductComponent },
  { path: 'orders', component: OrdersComponent },
  { path: 'customers', component: CustomersComponent },
  { path: 'history', component: HistoryComponent },
  { path: 'blogs', component: BlogsComponent },
  { path: 'swap-product', component: WishlistComponent },
  { path: 'feedbacks', component: FeedbacksComponent },
  { path: 'edit-blog/:id', component: EditBlogComponent },
  { path: 'shop-home', component: HomeShopComponent },
  { path: 'related-product', component: RelatedProductComponent },
  { path: 'home-blog', component: HomeBlogComponent },
  { path: 'employees', component: EmployeeComponent },
  { path: 'e-blogs', component: EBlogsComponent },
  { path: 'e-customers', component: ECustomersComponent },
  { path: 'e-nav', component: ENavComponent },
  { path: 'e-orders', component: EOrdersComponent },
  { path: 'e-feedbacks', component: EFeedbacksComponent },
  { path: 'e-products', component: EProductsComponent },
  { path: 'e-wishlists', component: EWishlistsComponent },
  { path: 'swap', component: SwapComponent },
  { path: 'add-swap', component: AddSwapComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
