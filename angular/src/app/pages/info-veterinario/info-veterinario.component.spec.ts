import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InfoVeterinarioComponent } from './info-veterinario.component';

describe('InfoVeterinarioComponent', () => {
  let component: InfoVeterinarioComponent;
  let fixture: ComponentFixture<InfoVeterinarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [InfoVeterinarioComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(InfoVeterinarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
