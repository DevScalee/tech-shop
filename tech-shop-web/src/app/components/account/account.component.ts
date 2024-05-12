import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';

import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { UserService } from 'src/app/services/user.service';
@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css'],
 
})
export class AccountComponent implements OnInit {
  user: any = {};
  id: any;
  msg: String = '';
  find_user: any;
  ok_password: any;
  message = true;
  user_login: any={};
  signupForm: FormGroup = new FormGroup({
    name: new FormControl(''),
    lastname: new FormControl(''),
    email: new FormControl(''),
    password: new FormControl(''),
    confirm_pwd: new FormControl(''),
    city: new FormControl(''),
    address: new FormControl(''),
    phoneNumber: new FormControl(''),
    postcode: new FormControl(''),
    // check: new FormControl('')
  });

  connectedUser: any = {};
  msgError: string = '';
  loginForm: FormGroup = new FormGroup({
    email: new FormControl(''),
    password: new FormControl(''),
  });

  constructor(
    private fb: FormBuilder,
    private formBuilder: FormBuilder,
    private router: Router,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.signupForm = this.fb.group({
      name: [''],
      lastname: [''],
      email: [''],
      password: [''],
      confirm_pwd: [''],
      postcode: [''],
      city: [''],
      address: [''],
      phoneNumber: [''],
    });

    this.loginForm = this.formBuilder.group({
      email: [''],
      password: [''],
    });
  }
id_user : any ;
  signup() {
    console.log('Here user', this.user);

    var val = this.checkData(this.user);
    if (val == true) {
      this.userService.signUp(this.user).subscribe((data) => {
        console.log('Here data from BE after add user', data);

        if (data == null || data == undefined) {
         
          this.msg = 'Email is already Exist';
        } else {
         this.id_user =  data.toString();
          localStorage.setItem('connectedUser',this.id_user);
          this.router.navigate(['']);
         
        }
      });
    } else {
      console.log('error');
    }
  }

  checkData(user: any) {
    // var element = <HTMLInputElement>document.getElementById('checkbox');
    var valid = true;
    //var x = this.validateEmail(this.user.email);
    // var y = this.validatePassword(this.user.pwd);
    if (user.name == undefined || user.lastname == undefined) {
      this.msg = 'Name is Required !';
      valid = false;
    } 
    // else if (this.validateEmail(this.user.email) == false) {
    //   this.msg = 'Please check you Email !';
    //   valid = false;
    // }  
    else if (user.confirm_pwd != user.password) {
      console.log('confirm pwd', user.confirm_pwd);
      this.msg = 'Please confirm your password !';
      valid = false;
    } else if (user.city == undefined) {
      this.msg = 'City is Required !';
      valid = false;
    } else if (user.address == undefined) {
      this.msg = 'Country is Required !';
      valid = false;
    } else if (user.postcode == undefined) {
      this.msg = 'Postcode is Required !';
      valid = false;
    } else if (user.phoneNumber == undefined) {
      this.msg = 'Phone Number is Required !';
      valid = false;
    }
    //else if (element.checked == false) {
    // this.msg = 'Please Accept the Pivacy Policy';
    //valid = false;
    //}

    return valid;
  }

  Login() {
    this.user_login.email=this.user.email;
    this.user_login.password=this.user.password;
  
    
    this.userService.logIn(this.user).subscribe((data) => {
      console.log('Data after login', data);
         

      this.user_login = data
       localStorage.setItem('connectedUser', this.user_login.id);
       this.router.navigate(['']).then(() => {
        window.location.reload();
      });
      //  const token = localStorage.getItem('token');
        // if (token == null) {
        //   this.router.navigate([`profile/${data.name}`]);
        // } else {
        //   this.router.navigate(['']);
        // }    
      
      });
    //});
  }

  forgot() {
    console.log('here in forgot Password ', this.user.email);
    if (this.user.email == undefined || this.user.email == '') {
      this.msgError = 'Please Verify your email !';
      this.message = false;
    } else {
      this.userService.getUserByEmail(this.user.email).subscribe((data) => {
        this.find_user = data.user;
        console.log(data.message);
        if (data.message != 'user not finding') {
          this.message = true;
          this.userService.forgotPassword(this.find_user).subscribe(
            (response: any) => {
              console.log(
                'An e-mail was sent to' +
                  this.find_user.email +
                  'Please check your mailbox.'
              );
              localStorage.setItem('forgotPassword', this.user.email);
            },
            (error) => {
              this.message = false;
              console.log(error);
            }
          );
        } else {
          this.message = false;
        }
      });
    }
  }
}