import { Component, ElementRef, HostListener, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  // constructor(public element: ElementRef) {}
  constructor(private router: Router){}

  @ViewChild('navbar') nav?: ElementRef; 

  ngOnInit(): void {
  }

  ngAfterViewInit(): void{
    //console.log(this.element.nativeElement.children[0]);
    this.navbarShrink();
    
  }

  navbarShrink(): void {
    if (!this.nav?.nativeElement) {
        return;
    }
    if (window.scrollY === 0) {
        this.nav.nativeElement.classList.remove('navbar-shrink')
    } else {
        this.nav.nativeElement.classList.add('navbar-shrink')
    }
  };

  @HostListener('window:scroll', ['$event']) // for window scroll events
  onScroll(event: any) {
    // this.navbarShrink();
  }

}
