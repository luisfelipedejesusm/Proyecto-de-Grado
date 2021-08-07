import { Component, HostListener, OnInit } from '@angular/core';
import { TokenStorageService } from './_services/token-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  private roles: string[] = [];
  isLoggedIn = false;
  isAdmin = false;
  isModerator = false;
  username ?: string;

  constructor(private tokenStorageService: TokenStorageService){}

  ngOnInit(): void {
    
    this.isLoggedIn = this.tokenStorageService.isLoggedIn();
    if(this.isLoggedIn){
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;

      this.isAdmin = this.roles.includes("ROLE_ADMIN");
      this.isModerator = this.roles.includes("ROLE_MODERATOR");

      this.username = user.username;
    }

  }

  

  logout(): void{
    this.tokenStorageService.signOut();
    window.location.reload();
  }

  title = 'DonantesRDv2';
}
