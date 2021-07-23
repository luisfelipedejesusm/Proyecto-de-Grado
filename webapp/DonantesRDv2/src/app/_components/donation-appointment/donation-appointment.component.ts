import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { DateAndTime } from 'src/app/_models/date-and-time.model';
import { DonationAppointment } from 'src/app/_models/donation-apointment.model';
import { DonationCenter } from 'src/app/_models/donation-center.model';
import { AppointmentService } from 'src/app/_services/appointment.service';
import { ResourceService } from 'src/app/_services/resource.service';

@Component({
  selector: 'app-donation-appointment',
  templateUrl: './donation-appointment.component.html',
  styleUrls: ['./donation-appointment.component.css']
})
export class DonationAppointmentComponent implements OnInit, OnDestroy {

  constructor(private resource: ResourceService, private appoinmentService: AppointmentService, private router: Router) { }

  bloodgroups: String[] = this.resource.getBloodGroups();
  donationCenters: DonationCenter[] = [];
  availableDateAndTime: DateAndTime[] | null = null;

  model: DonationAppointment = new DonationAppointment();
  subscriptions: Subscription = new Subscription();

  isSubmitted: boolean = false;
  showConfirmation: boolean = false;

  ngOnInit(): void {
    this.subscriptions.add(
      this.resource.getDonationCenters().subscribe(response => {
        this.donationCenters = response;
      })
    );
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
      this.model.appointmentDate = null;
      this.subscriptions.add(
        this.resource.getDonationCenterAppointmentDates(this.model.donationCenterId).subscribe(response => {
          this.availableDateAndTime = response;
        })
      );
    }
  }

  getAvailableHours(){
    let dat = this.availableDateAndTime?.filter(i=>i.date == this.model.appointmentDate).map(i => i.time.substr(0, 5));
    return this.resource.getWorkingHours(8, 18).filter(item => {
      return !dat?.includes(item.toString());
    });
  }

  getDonationCenter(donationCenterId: any): DonationCenter | undefined {
    return this.donationCenters.find(center => center.id == donationCenterId);
  }


}
