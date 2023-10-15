import axios from 'axios';

// eslint-disable-next-line @typescript-eslint/no-explicit-any
const axiosApi = (url: string, data?: any) => {
  const instance = axios.create({
    baseURL: url,
    withCredentials: true,
    headers: {
      Authorization: `Bearer ${localStorage.getItem('token')}`,
    },
    ...data,
  });

  return instance;
};

export const defaultInstance = axiosApi(
  'http://glogglogglog-env.eba-fuksumx7.ap-northeast-2.elasticbeanstalk.com',
);
