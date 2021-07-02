import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AppService } from "../service";
import {environment} from "../../environments/environment";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  greeting = "";

  constructor(private app: AppService, private http: HttpClient) {
    http.get(environment.myEndpoint + 'index').subscribe((response: any) => this.greeting = response.data);
  }

  ngOnInit(): void {
  }

  authenticated() {
    return this.app.authenticated;
  }



}
