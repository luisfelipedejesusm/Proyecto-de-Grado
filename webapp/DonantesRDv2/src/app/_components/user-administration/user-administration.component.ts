import { Component, OnDestroy, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Subscription } from 'rxjs';
import { User } from 'src/app/_models/user.model';
import { AdministrationService } from 'src/app/_services/administration.service';

@Component({
  selector: 'app-user-administration',
  templateUrl: './user-administration.component.html',
  styleUrls: ['./user-administration.component.css']
})
export class UserAdministrationComponent implements OnInit, OnDestroy {

  constructor(private administrationService: AdministrationService, private modalService: NgbModal) { }

  users: User[] = [];
  subscriptions: Subscription = new Subscription();

  model: User = new User();

  ngOnInit(): void {
    this.subscriptions.add(
      this.administrationService.getUsers().subscribe(response => {
        this.users = response;
        console.log(this.users);
      })
    );
  }

  update(user: User, model: any){
    this.model = user;
    this.modalService.open(model);
  }

  ngOnDestroy(){
    this.subscriptions.unsubscribe();
  }

  updateUserInformation(modal: any){
    this.subscriptions.add(
      this.administrationService.updateUser(this.model).subscribe()
    );
    modal.close();
  }

  clear(modal: any){

  }
  

}
