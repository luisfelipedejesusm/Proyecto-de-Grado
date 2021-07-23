import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { DonationAppointment } from 'src/app/_models/donation-apointment.model';
import { AppointmentService } from 'src/app/_services/appointment.service';

@Component({
  selector: 'app-my-appointments',
  templateUrl: './my-appointments.component.html',
  styleUrls: ['./my-appointments.component.css']
})
export class MyAppointmentsComponent implements OnInit, OnDestroy {

  constructor(private appointmentService: AppointmentService) { }
  
  subscriptions: Subscription = new Subscription();
  myAppointments: DonationAppointment[] = [];

  ngOnInit(): void {
    this.subscriptions.add(
      this.appointmentService.getMyAppointments().subscribe(response => {
        this.myAppointments = response;
      })
    );
  }

  ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }

}
