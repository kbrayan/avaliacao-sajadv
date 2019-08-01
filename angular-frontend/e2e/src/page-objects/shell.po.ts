/*
 * Use the Page Object pattern to define the page under test.
 * See docs/coding-guide/e2e-tests.md for more info.
 */

import { browser, element, by } from 'protractor';

export class ShellPage {
  welcomeText = element(by.css('app-root mat-card-title'));
  newPersonButton = element(by.id('btnRegisterNewPerson'));
  peopleListButton = element(by.id('btnPeopleList'));
  aboutButton = element(by.id('btnAbout'));

  getParagraphText() {
    return this.welcomeText.getText();
  }
}
