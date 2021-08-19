import { Component, OnInit } from '@angular/core';
import { DataService } from 'src/app/_services/data.service';
import { AuthService } from '../../_services/auth.service';
import { TokenStorageService } from '../../_services/token-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor( private authService: AuthService, private tokenStorage: TokenStorageService, public data: DataService) { }

  form: any = {
    username: null,
    password: null
  };

  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  username: string = "";

  ngOnInit(): void {
    if(this.tokenStorage.isLoggedIn()){
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;
      this.username = this.tokenStorage.getUser().username;
    }

  }

  onSubmit(): void{
    const {username, password} = this.form;
    this.authService.signin(username, password).subscribe(data => {
      this.tokenStorage.saveToken(data.token);
      this.tokenStorage.saveUser(data.user);

      this.isLoggedIn = false;
      this.isLoginFailed = false;

      this.roles = this.tokenStorage.getUser().roles;
      this.username = this.tokenStorage.getUser().username;
      this.reloadPage();
    }, err => {
      this.errorMessage = err.error.message;
      this.isLoginFailed = true;
    })
  }
  reloadPage() {
    window.location.reload();
  }

}
