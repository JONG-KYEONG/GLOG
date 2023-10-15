'use client';
import { Stack } from '@mui/material';
import React, { useEffect, useState } from 'react';
import { BlackContainer, ImageContainer, ThumbnailArea } from './postId.style';
import profilePic from '/public/assets/test.png';
import DragAndDrop from '@/components/DND/DragAndDrop';
import { useGetSidebarQuery, usePostPostQuery } from '@/api/blog-api';
import { IPostContent, ISidebarContent, ISidebarFileContent } from '@/types/dto';

const page = ({ params }: { params: { titleId: string; postId: string } }) => {
  const { data: sidebarData } = useGetSidebarQuery({ blogId: 3 });
  const { data: postData } = usePostPostQuery({ postId: Number(params.postId) });

  const [writeList, setWriteList] = useState<ISidebarFileContent[]>();
  const [post, setPost] = useState<IPostContent>();

  const sidebarContent: ISidebarContent[] = sidebarData?.sidebarDtos;

  useEffect(() => {
    setWriteList(
      sidebarContent
        ?.map((item) => ({
          categoryId: item.categoryId,
          postTitleDtos: item.postTitleDtos,
        }))
        .filter((items) => Number(params.titleId) === items.categoryId)
        .map((filteredItem) => filteredItem.postTitleDtos)[0],
    );

    setPost(postData);
  }, [sidebarData, postData]);

  return (
    <Stack>
      <ThumbnailArea>
        <ImageContainer src={profilePic} fill alt="Picture of the author" />
        <BlackContainer></BlackContainer>
      </ThumbnailArea>
      <Stack>
        {
          sidebarContent?.filter((item) => item.categoryId === Number(params.titleId))[0]
            .categoryName
        }
      </Stack>
      <DragAndDrop
        footprintList={writeList}
        categoryNumber={params.titleId}
        rightContainer={
          <Stack width={'100%'} height={'100vh'} bgcolor="white">
            <Stack>{post?.content}</Stack>
          </Stack>
        }
      />
    </Stack>
  );
};

export default page;
