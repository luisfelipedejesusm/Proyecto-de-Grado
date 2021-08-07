import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { DonationCenter } from 'src/app/_models/donation-center.model';
import { User } from 'src/app/_models/user.model';
import { ResourceService } from 'src/app/_services/resource.service';

@Component({
  selector: 'app-masterhead',
  templateUrl: './masterhead.component.html',
  styleUrls: ['./masterhead.component.css']
})
export class MasterheadComponent implements OnInit, OnDestroy {

  constructor(private resources: ResourceService) { }

  lat = 18.5132572;
  lng = -69.8851534;

  subscriptions: Subscription = new Subscription();
  donationCenters: DonationCenter[] = [];
  donors: User[] = [];

  ngOnInit(): void {
    this.subscriptions.add(
      this.resources.getDonationCenters().subscribe(response => {
        this.donationCenters = response;
      })
    );
    this.subscriptions.add(
      this.resources.getPublicDonors().subscribe(response => {
        this.donors = response;
      })
    );
  }

  ngOnDestroy(){
    this.subscriptions.unsubscribe();
  }

}
