import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { DonationAppointment } from 'src/app/_models/donation-apointment.model';
import { User } from 'src/app/_models/user.model';
import { ResourceService } from 'src/app/_services/resource.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit, OnDestroy {

  constructor(private token: TokenStorageService, private userService: UserService) { }
  
  ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }

  user!: User;
  subscriptions: Subscription = new Subscription();
  lastDonation: DonationAppointment = new DonationAppointment();


  ngOnInit(): void {
    this.user = new User().deserialize(this.token.getUser());
    this.subscriptions.add(
      this.userService.getLastDonation(this.user.id).subscribe(response => {
        this.lastDonation = new DonationAppointment().deserialize(response);
      })
    )
  }

}
