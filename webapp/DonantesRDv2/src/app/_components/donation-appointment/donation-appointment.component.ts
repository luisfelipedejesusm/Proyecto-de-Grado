import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Campaign } from 'src/app/_models/campaign.model';
import { DateAndTime } from 'src/app/_models/date-and-time.model';
import { DonationAppointment } from 'src/app/_models/donation-apointment.model';
import { DonationCenter } from 'src/app/_models/donation-center.model';
import { User } from 'src/app/_models/user.model';
import { AppointmentService } from 'src/app/_services/appointment.service';
import { DataService } from 'src/app/_services/data.service';
import { PermissionService } from 'src/app/_services/permission.service';
import { ResourceService } from 'src/app/_services/resource.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'app-donation-appointment',
  templateUrl: './donation-appointment.component.html',
  styleUrls: ['./donation-appointment.component.css']
})
export class DonationAppointmentComponent implements OnInit, OnDestroy {

  constructor(
    private resource: ResourceService, 
    private appoinmentService: AppointmentService, 
    private permission: PermissionService,
    private token: TokenStorageService,
    public data: DataService) { }

  bloodgroups: String[] = this.resource.getBloodGroups();
  donationCenters: DonationCenter[] = [];
  availableDateAndTime: DateAndTime[] | null = null;

  model: DonationAppointment = new DonationAppointment();
  subscriptions: Subscription = new Subscription();

  isSubmitted: boolean = false;
  showConfirmation: boolean = false;
  campaign!: Campaign | null;

  authenticated = this.permission.authenticated;

  ngOnInit(): void {
    this.subscriptions.add(
      this.resource.getDonationCenters().subscribe(response => {
        this.donationCenters = response;
      })
    );

    if(this.authenticated){
      let user: User = this.token.getUser();
      this.model.userid = user.id;
      Object.assign(this.model, user);
      this.model.bloodGroup = user.bloodType;
    }

    if(this.data.campaign){
      this.campaign = this.data.campaign;
      this.data.campaign = null;
      this.model.campaignId = this.campaign.id;
    }
    
  }

  newDonationAppointment(){
    this.isSubmitted = true;
    this.subscriptions.add( this.appoinmentService.createDonationAppointment(this.model).subscribe(response => {
      // TODO: add other type of alert
      alert("created");
      this.showConfirmation = true;
    }) );
  }

  ngOnDestroy(){
    this.subscriptions.unsubscribe();
  }

  updateDonationCenterInformation(){
    if(this.model.donationCenterId){
      this.model.dateAppointment = null;
      this.subscriptions.add(
        this.resource.getDonationCenterAppointmentDates(this.model.donationCenterId).subscribe(response => {
          this.availableDateAndTime = response;
        })
      );
    }
  }

  getAvailableHours(){
    let dat = this.availableDateAndTime?.filter(i=>i.date == this.model.dateAppointment).map(i => i.time.substr(0, 5));
    return this.resource.getWorkingHours(8, 18).filter(item => {
      return !dat?.includes(item.toString());
    });
  }

  getDonationCenter(donationCenterId: any): DonationCenter | undefined {
    return this.donationCenters.find(center => center.id == donationCenterId);
  }

  


}
