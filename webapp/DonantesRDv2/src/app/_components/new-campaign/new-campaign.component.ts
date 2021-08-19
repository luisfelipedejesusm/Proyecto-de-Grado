import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Campaign } from 'src/app/_models/campaign.model';
import { ResourceService } from 'src/app/_services/resource.service';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-new-campaign',
  templateUrl: './new-campaign.component.html',
  styleUrls: ['./new-campaign.component.css']
})
export class NewCampaignComponent implements OnInit, OnDestroy {

  constructor( private resource: ResourceService, private userService: UserService, private router: Router) { }

  ngOnDestroy(): void {
    if(this.subscriptions)
      this.subscriptions.unsubscribe();
  }

  model: Campaign = new Campaign();
  bloodgroups: String[] = this.resource.getBloodGroups();

  subscriptions: Subscription = new Subscription();


  ngOnInit(): void {
    this.model.bloodType = "ALL";
  }

  uploadCampaignPhoto(ev: any){
    var reader = new FileReader();
      reader.onload = (e:any) =>{
        this.model.photoUrl = e.target.result;
      };
      reader.readAsDataURL(ev.target.files[0]);
  }

  newCampaign(){
    this.subscriptions.add(
      this.userService.newCampaing(this.model).subscribe(response => {
        alert("Saved");
        this.router.navigate(["donation-center/campaigns"]);
      })
    );
  }
}
