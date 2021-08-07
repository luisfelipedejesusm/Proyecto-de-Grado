import { Component, ElementRef, HostListener, OnInit, ViewChild } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { PermissionService } from 'src/app/_services/permission.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  shouldShow = true;
  shouldBeTransparent = true;
  authenticated = this.token.isLoggedIn();

  // constructor(public element: ElementRef) {}
  constructor(private router: Router, private token: TokenStorageService, private permissions: PermissionService){
    router.events.subscribe(val => {
      if(val instanceof NavigationEnd){
        if(val.url.indexOf('/signup') >= 0 || val.url.indexOf('/login') >= 0){
          this.shouldShow = false;
          this.nav?.nativeElement && this.nav.nativeElement.classList.remove('navbar-shrink');
        }else if(val.url.indexOf("/home") < 0){
          this.shouldBeTransparent = false;
          this.nav?.nativeElement && this.nav.nativeElement.classList.add('navbar-shrink');
        }else{
          this.shouldBeTransparent = true;
          this.shouldShow = true;
          this.nav?.nativeElement && this.nav.nativeElement.classList.remove('navbar-shrink');
        }
      }
    })
  }

  @ViewChild('navbar') nav?: ElementRef; 

  ngOnInit(): void {
  }

  ngAfterViewInit(): void{
    //console.log(this.element.nativeElement.children[0]);
    if(this.shouldBeTransparent) this.navbarShrink();
    
  }

  navbarShrink(): void {
    if (!this.nav?.nativeElement) {
        return;
    }
    if (window.scrollY === 0) {
        this.nav.nativeElement.classList.remove('navbar-shrink');
    } else {
        this.nav.nativeElement.classList.add('navbar-shrink');
    }
  };

  @HostListener('window:scroll', ['$event']) // for window scroll events
  onScroll(event: any) {
    if(this.shouldBeTransparent) this.navbarShrink();
  }

  ifAuth(url: string, fallback: string): string{
    if(this.authenticated){
      return url;
    }
    return fallback;
  }

  hasPermision(role: string): boolean{
    return this.permissions.canAccess(role);
  }

  usertype(type: string): boolean{
    return this.permissions.usertype(type);
  }

  logout(){
    this.token.signOut();
    window.location.href = "/home";
  }

}
