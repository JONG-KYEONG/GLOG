'use client';
import { Stack } from '@mui/material';
import React from 'react';
import { BlackContainer, ImageContainer, ThumbnailArea } from './postId.style';
import profilePic from '/public/assets/test.png';
import DragAndDrop from '@/components/DND/DragAndDrop';

const page = ({ params }: { params: { titleId: string; postId: string } }) => {
  const writeList = [
    {
      postId: 0,
      postTitle: '제목입니다',
    },
    {
      postId: 1,
      postTitle: '프론트앤드',
    },
    {
      postId: 2,
      postTitle: '백앤드',
    },
  ];

  const backendInfo = [
    {
      PostDetailResponse: {
        author: {
          userId: 1,
          nickname: 'string',
          profileImage: 'string',
        },
        blogUrl: 'string',
        postId: 1,
        title: '제목입니다.',
        content: 'string',
        thumbnail: 'string',
        createdAt: 0,
        likesCount: 0,
        viewsCount: 0,
        repliesCount: 0,
        isPrivate: true,
        isScraped: false,
        isLiked: false,
        isAuthor: false,
        hastags: [],
      },
    },
  ];
  const result = backendInfo[0];

  return (
    <Stack>
      <ThumbnailArea>
        <ImageContainer src={profilePic} fill alt="Picture of the author" />
        <BlackContainer></BlackContainer>
      </ThumbnailArea>

      <DragAndDrop
        footprintList={writeList}
        categoryNumber={params.postId}
        rightContainer={
          <Stack width={'100%'} height={'100vh'} bgcolor="white">
            <Stack>{result.PostDetailResponse.content}</Stack>
          </Stack>
        }
      />
    </Stack>
  );
};

export default page;
