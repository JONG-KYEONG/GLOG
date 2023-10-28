import { Stack } from '@mui/material';
import { styled } from '@mui/material/styles';

export const TopStack = styled(Stack)(({theme}) => ({
    borderBottom: `1px solid ${theme.palette.primary.main}`,
    justifyContent: "space-between",
    flexDirection: "row",
    marginBottom: "10px",
}));

//나중에 button으로 변경해야함
export const Sort = styled(Stack) ({
    padding: '0px',
    width: '24px',
})

const ProfileImage = styled(Stack)(({ imageSrc }: {imageSrc: string}) => ({
    backgroundColor: 'teal',
    backgroundAttachment: 'fixed',
    backgroundImage: `url(${imageSrc})`,
    backgroundRepeat: "no-repeat",
    width: '30px',
    height: '30px',
    borderRadius: '50%',
}));

function FriendListComponent({
    nickname,
    profileImg,
}: {nickname: string, profileImg: string}) {
    return (
        <Stack
        margin="6px 0"
        flexDirection="row">
            <ProfileImage
                imageSrc={profileImg}
            />
            <Stack
            marginLeft="5px">
                {nickname}
            </Stack>
        </Stack>
    )
}



export default FriendListComponent;