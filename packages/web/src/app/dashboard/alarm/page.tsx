import { getServerSession } from 'next-auth';
import { options } from '@/app/api/auth/[...nextauth]/options';
import AlarmListContainer from '@/containers/alarm/AlarmListContainer';

export default async function Page() {
    const session = await getServerSession(options);
    const { accessToken } = session?.user?.data || {};

    return (
        <div>
            {accessToken && <AlarmListContainer accessToken={accessToken} />}
        </div>
    );
}