import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from "@angular/router";
import { TokenStorageService } from "../_services/token-storage.service";

@Injectable()
export class AuthGuardService implements CanActivate {

    private permission: String = "ROLE_ADMIN";
 
    constructor(private router:Router, private token: TokenStorageService ) {
 
    }
 
    canActivate(route: ActivatedRouteSnapshot,
                state: RouterStateSnapshot): boolean|UrlTree {
 
        if (!this.token.isLoggedIn()) {
            console.log('You are not allowed to view this page. You are redirected to login Page');
            
            this.router.navigate(["login"],{ queryParams: { retUrl: route.url} });
            return false;
        } 
 
        return true;
    }
 
}