import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfirmDeleteTratamientoComponent } from './confirm-delete-tratamiento.component';

describe('ConfirmDeleteTratamientoComponent', () => {
  let component: ConfirmDeleteTratamientoComponent;
  let fixture: ComponentFixture<ConfirmDeleteTratamientoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ConfirmDeleteTratamientoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ConfirmDeleteTratamientoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
