import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SharedService {

  constructor() { }
  private _apiUrl: String = 'http://localhost:8080/api/v1/';
  private _testUrl: String = 'http://localhost:8080/api/v1/test/';
  private _httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  getApiUrl(){
    return this._apiUrl;
  }
  getHttpOptions(){
    return this._httpOptions;
  }
}
