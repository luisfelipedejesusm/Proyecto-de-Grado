import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  @Input() tittle = "";
  @Input() text = "";
  @Input() href = "";

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  onClick(){
    if(this.href)
      this.router.navigate(["/administration/" + this.href]);
  }

}
