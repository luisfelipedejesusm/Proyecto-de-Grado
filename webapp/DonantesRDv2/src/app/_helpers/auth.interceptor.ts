import { HTTP_INTERCEPTORS, HttpEvent } from "@angular/common/http";
import { HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';

import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { TokenStorageService } from "../_services/token-storage.service";


const TOKEN_HEADER_KEY = 'Authorization';  

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
    constructor(private token: TokenStorageService){};

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>>{
        let authRequest = request;
        const token = this.token.isLoggedIn()? this.token.getToken() : null;
        if(token != null){
            authRequest = request.clone({ headers: request.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + token) });
        }
        return next.handle(authRequest);
    }
}

export const authInterceptorProviders = [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
];