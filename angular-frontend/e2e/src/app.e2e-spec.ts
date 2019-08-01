import { browser, ExpectedConditions as until } from 'protractor';
import { AppSharedPage } from './page-objects/app-shared.po';
import { ShellPage } from './page-objects/shell.po';

describe('when the app loads', () => {
  const app = new AppSharedPage();
  const shell = new ShellPage();

  beforeAll(async () => {
    await app.navigateAndSetLanguage();
  });

  it('should display the shell page', async () => {
    expect(await browser.getCurrentUrl()).toContain('/');
  });

  describe('and the page loads', () => {
    it('should display the hello message', async () => {
      await browser.wait(
        until.visibilityOf(shell.welcomeText),
        5000,
        'Element taking too long to appear'
      );
      expect(await shell.getParagraphText()).toEqual('Hello world !');
    });
  });

  it('should navigate to the about page when the about button is clicked', async () => {
    await shell.aboutButton.click();

    expect(await browser.getCurrentUrl()).toContain('/about');
  });

  it('should navigate to the peoplelist page when the about button is clicked', async () => {
    await shell.peopleListButton.click();

    expect(await browser.getCurrentUrl()).toContain('/pessoa-lista');
  });

  it('should navigate to the pessoacriar page when the about button is clicked', async () => {
    await shell.newPersonButton.click();

    expect(await browser.getCurrentUrl()).toContain('/pessoa-criar');
  });
});
