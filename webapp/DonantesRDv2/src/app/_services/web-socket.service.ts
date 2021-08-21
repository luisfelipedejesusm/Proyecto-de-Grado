import { Injectable } from '@angular/core';
import {Stomp} from '@stomp/stompjs';
import * as SockJs from 'sockjs-client';

@Injectable({
  providedIn: 'root'
})
export class WebSocketService {
  

  // Open connection with the back-end socket
  public connect() {
    let socket = new SockJs(`http://localhost:8080/socket`);

    let stompClient = Stomp.over(socket);

    return stompClient;
  }
}
