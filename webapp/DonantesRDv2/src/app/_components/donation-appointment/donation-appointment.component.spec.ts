import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DonationAppointmentComponent } from './donation-appointment.component';

describe('DonationAppointmentComponent', () => {
  let component: DonationAppointmentComponent;
  let fixture: ComponentFixture<DonationAppointmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DonationAppointmentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DonationAppointmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
