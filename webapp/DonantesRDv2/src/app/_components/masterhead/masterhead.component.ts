import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Campaign } from 'src/app/_models/campaign.model';
import { DonationCenter } from 'src/app/_models/donation-center.model';
import { User } from 'src/app/_models/user.model';
import { DataService } from 'src/app/_services/data.service';
import { ResourceService } from 'src/app/_services/resource.service';

@Component({
  selector: 'app-masterhead',
  templateUrl: './masterhead.component.html',
  styleUrls: ['./masterhead.component.css']
})
export class MasterheadComponent implements OnInit, OnDestroy {

  constructor(private resources: ResourceService, public data: DataService, private router: Router) { }

  lat = 18.5132572;
  lng = -69.8851534;

  subscriptions: Subscription = new Subscription();
  donationCenters: DonationCenter[] = [];
  campaigns: Campaign[] = [];
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
    this.subscriptions.add(
      this.resources.getLastCampaigns().subscribe(response => {
        this.campaigns = response;
      })
    );
  }

  ngOnDestroy(){
    this.subscriptions.unsubscribe();
  }

  donateWithCampaign(campaign: Campaign){
    this.data.campaign = campaign;
    this.router.navigate(["donate-blood/appointment"]);
  }

}
