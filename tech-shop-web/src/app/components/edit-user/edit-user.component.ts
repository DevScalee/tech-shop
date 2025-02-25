import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {
  id: any;
  valid :any;
  ok: any;
  k : any ;
  message : String = "";
  msg: String = "";
  user: any;
  userForm: FormGroup = new FormGroup({
    name: new FormControl(''),
    lastName: new FormControl(''),
    email: new FormControl(''),
    password: new FormControl(''),
    new_pwd: new FormControl(''),
    confirm_pwd: new FormControl(''),
    address: new FormControl(''),
    city: new FormControl(''),
    phoneNumber: new FormControl(''),
    postcode: new FormControl('')

  });
  constructor(
    private userService: UserService,
    private formBuilder: FormBuilder,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.userForm = this.formBuilder.group({
      name: [''],
      lastName: [''],
      email: [''],
      password: [''],
      new_pwd: [''],
      confirm_pwd: [''],
      city: [''],
      address: [''],
      postcode: [''],
      phoneNumber: ['']
    })

    this.id = this.activatedRoute.snapshot.paramMap.get('id');
    this.getUser(this.id);




  }

  getUser(id: any) {
    this.userService.getUserById(id).subscribe(
      (data) => {
        this.user = data;
        this.user.postcode = 2096
        this.user.city = "Tunisie"
        console.log('user : ', this.user);
      }
    )
  }

   checkData(user : any) {  
     var oo = false;
 
  this.userService.decryptPwd(this.user).subscribe(
     (data) => {
     if (data) {
       console.log('data.message', data.message);
       if (data.message == "0") {
         this.msg = "Your Cuurent Password is incorrect !";
         this.ok = oo ;
       }
       else {
          oo = true; 
        this.ok = oo ;
       }
     }
     }
    )
     console.log('user.fname',user.name);
     var v = false ;
    if (user.new_pwd != user.confirm_pwd){
      this.msg = "Confirm Your New Password !"
      this.valid = v;
    }
    else if ( user.new_pwd != undefined  && this.validatePassword(this.user.new_pwd) == false ){
      this.msg = "Password between 7 to 16 characters : only characters, numeric digits, underscore and first character must be a letter ";
      this.valid = v;
    }
    else {
      v = true;
      this.valid = v;
    }

    var g = false ;
    if(user.name == "" || user.lastname == ""){
      this.msg = "Name is required !"
      g = false ;
      this.k = g;
    } 
    else if (user.email == undefined || this.validateEmail(user.email) == false  ){
      this.msg="Check Your Email ";
      g = false ;
      this.k = g;
    }
    else if( user.city == ""){
      this.msg="City is Required ! ";
      g = false ;
      this.k = g;
    }
    else if( user.address == ""){
      this.msg="Country is Required ! ";
      g = false ;
      this.k = g;
    }
    else if( user.postcode == ""){
      this.msg="Postcode is Required ! ";
      g = false ;
      this.k = g;
    }
    else if( user.phoneNumber == ""){
      this.msg="Phone Number is Required ! ";
      g = false ;
      this.k = g;
    }
    else {
      g = true ;
      this.k= g;
    }
   }
  
response : any ;
  EditUser() {
    this.checkData(this.user);
    console.log('this.ok :' ,this.ok);
    console.log('this.valid :' ,this.valid);
    console.log('this.k :' ,this.k);
    if (this.ok ==true && this.valid == true && this.k == true) 
    {
      if (this.user.new_pwd != undefined){
        this.user.password = this.user.new_pwd;
      }
        console.log('Here my form values ', this.user);
        this.userService.editUser(this.user).subscribe(
          (data) => {
            console.log(' here Data after editing', data);
            this.response = data 
             this.router.navigate([`profile/${this.response.id}`]);
          }
        );
      }
      else {
        console.log('error');
      }
  }

  validatePassword(pwd: String) {
    const passw = /^[A-Za-z]\w{7,14}$/;
    return passw.test(String(pwd.toLocaleLowerCase()));
  }

  validateEmail(email: string) {
    const regularExpression = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return regularExpression.test(String(email).toLowerCase());
  }


}
