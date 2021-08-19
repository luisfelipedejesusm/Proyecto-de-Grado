import { Injectable } from '@angular/core';
import { TokenStorageService } from './token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class PermissionService {

  usertype(type: string): boolean {
    if(type == '' && !this.token.isLoggedIn()) return true;
    return this.token.getUser().userType == type;
  }

  constructor(private token: TokenStorageService) { }

  private roles: String[] = this.token.getUser().roles;

  canAccess(role: string): boolean {
    return this.roles.map((a: any) => a.name).includes(role);
  }

  public authenticated: boolean = this.token.isLoggedIn();

}
