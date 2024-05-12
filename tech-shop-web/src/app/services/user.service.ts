import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';
const crudApi='http://localhost:9050'

@Injectable({
  providedIn: 'root',
})
export class UserService {
  userUrl: string = 'http://localhost:9050';

  constructor(private httpClient: HttpClient) {}
  signUp(user: any) {
    return this.httpClient.post(
      `${this.userUrl}/api/signup`,
      user
    );
  }

  logIn(user: any) {
    return this.httpClient.post(
      `${this.userUrl}/api/login`,user
    );
  }

  editUser(user: any) {
    return this.httpClient.put(
      `${this.userUrl}/api/updateuser/${user.id}`,
       user.id,
      user
    );
  }
/*  getAllUsers() {
    return this.httpClient?.get<{ users: any}>(
     `${this.userUrl}/api/users`
    );
  } */


  getAllUsers(): Observable<any> {
    return this.httpClient.get(crudApi + "/api/users");
  }


  getUserById(id: any) {
    return this.httpClient?.get(
      `${this.userUrl}/api/user/${id}`
    );
  }
  getUserByEmail(email: any) {
    return this.httpClient?.get<{ user: any; message: String }>(
      `${this.userUrl}/api/user/email/${email}`
    );
  }

  deleteUser(id: any) {
    return this.httpClient?.delete<{ message: string }>(
      `${this.userUrl}/api/user/users/${id}`
    );
  }
  decryptPwd(pwd: any) {
    return this.httpClient?.post<{ message: string }>(
      `${this.userUrl}/api/user/decrypt/pwd`,
      pwd
    );
  }
  forgotPassword(data: any) {
    return this.httpClient.post<{ token: any }>(
      `${this.userUrl}/api/forgotPassword/forgot-password`,
      data
    );
  }

  resetPassoword(resetData: any) {
    return this.httpClient.post(
      `${this.userUrl}/api/resetPassword/reset-password`,
      resetData
    );
  }
  public saveToken(token: string): void {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  NumberOfOrders(id: any) {
    return this.httpClient.get<{ number: any }>(
      `${this.userUrl}/api/user/orders/${id}`
    );
  }
}
