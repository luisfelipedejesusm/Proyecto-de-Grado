import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DcAppointmentsComponent } from './dc-appointments.component';

describe('DcAppointmentsComponent', () => {
  let component: DcAppointmentsComponent;
  let fixture: ComponentFixture<DcAppointmentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DcAppointmentsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DcAppointmentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
