import { ThemeType } from '@/types/common';

export const oppositeThemeExtraction = (theme: ThemeType) => {
  return theme === 'dark' ? 'light' : 'dark';
};

export const getCurrentThemeClass = (
  userTheme: ThemeType,
  light: string | number,
  dark: string | number,
) => {
  return userTheme === 'light' ? light : dark;
};

export const API_BASE_URL =
  'http://glogglogglog-env.eba-fuksumx7.ap-northeast-2.elasticbeanstalk.com';

export const OAUTH2_REDIRECT_URI = 'http://localhost:3000/oauth2/redirect';

export const GOOGLE_AUTH_URL =
  API_BASE_URL + '/oauth2/authorization/google?redirect_uri=' + OAUTH2_REDIRECT_URI;
export const GITHUB_AUTH_URL =
  API_BASE_URL + '/oauth2/authorization/github?redirect_uri=' + OAUTH2_REDIRECT_URI;
