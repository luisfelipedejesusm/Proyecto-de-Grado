import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { DonationAppointment } from 'src/app/_models/donation-apointment.model';
import { AppointmentService } from 'src/app/_services/appointment.service';

@Component({
  selector: 'app-dc-appointments',
  templateUrl: './dc-appointments.component.html',
  styleUrls: ['./dc-appointments.component.css']
})
export class DcAppointmentsComponent implements OnInit, OnDestroy {



  constructor(private appointmentService: AppointmentService) { }
  
  subscriptions: Subscription = new Subscription();
  myAppointments: DonationAppointment[] = [];

  ngOnInit(): void {
    this.subscriptions.add(
      this.appointmentService.getDcAppointments().subscribe(response => {
        this.myAppointments = response;
      })
    );
  }

  ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }
  
  cancelAppointment(id: any){
    if(confirm("Do you want to cancel this appointment?")){
      this.subscriptions.add(
        this.appointmentService.cancelAppointment(id).subscribe(response => {
          this.myAppointments = this.myAppointments.filter(a => a.id != id);
        })
      );
    }
  }

}
