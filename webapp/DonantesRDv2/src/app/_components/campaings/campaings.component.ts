import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Campaign } from 'src/app/_models/campaign.model';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-campaings',
  templateUrl: './campaings.component.html',
  styleUrls: ['./campaings.component.css']
})
export class CampaingsComponent implements OnInit, OnDestroy {

  constructor(private userService: UserService) { }

  campaigns: Campaign[] = [];
  subscriptions: Subscription = new Subscription();

  ngOnInit(): void {
    this.subscriptions.add(
      this.userService.getCampaigns().subscribe(response => {
        this.campaigns = response;
      })
    );
  }

  ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }

  changeActive(campaign: Campaign){
    campaign.isOpen = !campaign.isOpen;
    this.subscriptions.add(
      this.userService.campaignActivation(campaign.id).subscribe()
    );
  }

}
