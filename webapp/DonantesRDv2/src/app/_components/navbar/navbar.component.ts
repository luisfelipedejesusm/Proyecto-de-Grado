import { Component, ElementRef, HostListener, OnInit, ViewChild } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { Campaign } from 'src/app/_models/campaign.model';
import { DataService } from 'src/app/_services/data.service';
import { PermissionService } from 'src/app/_services/permission.service';
import { SharedService } from 'src/app/_services/shared.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { WebSocketService } from 'src/app/_services/web-socket.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  shouldShow = true;
  shouldBeTransparent = true;
  authenticated = this.token.isLoggedIn();

notifications: any[] = [];


  // constructor(public element: ElementRef) {}
  constructor(private router: Router, private token: TokenStorageService, private permissions: PermissionService, public data: DataService, private webSocketService: WebSocketService, private shared: SharedService){
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

    if(token.getUser() &&  token.getUser().notifications)
      this.notifications = token.getUser().notifications.sort((a: any, b: any) => <any>new Date(b.dateAndTime) - <any>new Date(a.dateAndTime));

    // Open connection with server socket
    let stompClient = this.webSocketService.connect();
    // stompClient.connect({}, (frame: any) => {
      let _this = this;

    stompClient.connect({}, function (frame: any) {
      // _this.setConnected(true);
      // console.log('Connected: ' + frame);
      let userid = token.getUser().id;
      if(userid)
        stompClient.subscribe('/topic/notification/'+userid, function (hello) {
          // console.log(hello)
          _this.notifications.unshift(JSON.parse(hello.body));
          token.updateNotifications(_this.notifications);
        });
    // });

  // Subscribe to notification topic
        // stompClient.subscribe('/topic/notification', (notification: any) => {

    // Update notifications attribute with the recent messsage sent from the server
            // this.notifications.push(JSON.parse(notification.body));
        // })
    });
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

  openCampaign(notification: any){
    this.data.campaign = notification.campaign;
    this.router.navigate(["donate-blood/appointment"]);
  }

}
