import { Component, OnInit } from '@angular/core';
import { Campaign } from 'src/app/_models/campaign.model';
import { ResourceService } from 'src/app/_services/resource.service';

@Component({
  selector: 'app-new-campaign',
  templateUrl: './new-campaign.component.html',
  styleUrls: ['./new-campaign.component.css']
})
export class NewCampaignComponent implements OnInit {

  constructor( private resource: ResourceService) { }

  model: Campaign = new Campaign();
  bloodgroups: String[] = this.resource.getBloodGroups();


  ngOnInit(): void {
  }

  uploadCampaignPhoto(ev: any){
    var reader = new FileReader();
      reader.onload = (e:any) =>{
        this.model.imageUrl = e.target.result;
      };
      reader.readAsDataURL(ev.target.files[0]);
  }
}
