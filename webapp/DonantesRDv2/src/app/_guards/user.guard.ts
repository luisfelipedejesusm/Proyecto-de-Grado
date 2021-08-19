import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from "@angular/router";
import { PermissionService } from "../_services/permission.service";
import { TokenStorageService } from "../_services/token-storage.service";

@Injectable()
export class UserGuardService implements CanActivate {

 
    constructor(private router:Router, private token: TokenStorageService, private permission: PermissionService ) {
 
    }
 
    canActivate(route: ActivatedRouteSnapshot,
                state: RouterStateSnapshot): boolean|UrlTree {


        if (!this.permission.usertype("USER")) {
            console.log('You are not allowed to view this page. You are redirected to Home Page');
            
            this.router.navigate(["home"]);
            return false;
        } 
 
        return true;
    }
 
}