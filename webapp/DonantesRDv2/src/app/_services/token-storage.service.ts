import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {
  constructor() { }

  jwtHelper = new JwtHelperService();

  signOut(): void {
    window.sessionStorage.clear();
  }

  public saveToken(token: string): void {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string | null {
    return window.sessionStorage.getItem(TOKEN_KEY);
  }

  public saveUser(user: any): void {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  public getUser(): any {
    const user = window.sessionStorage.getItem(USER_KEY);
    if (user) {
      return JSON.parse(user);
    }
    return {};
  }

  public isLoggedIn(): boolean{
    let token = window.sessionStorage.getItem(TOKEN_KEY);
    if(token)
      return !this.jwtHelper.isTokenExpired(token);
    return false;
  }

  public updateNotifications(notifications: any){
    let user: any = window.sessionStorage.getItem(USER_KEY);
    if(user){
      user = JSON.parse(user)
      user.notifications = notifications;
      window.sessionStorage.removeItem(USER_KEY);
      window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
    }
  }
  
}
