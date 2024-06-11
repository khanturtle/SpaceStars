import './teamcard.css'

type UserDataType = {
  userId: number
  username?: string
  profileImage?: string
}

export interface UserAvatarProps {
  className?: string
  size?: 'small' | 'medium'
  users?: UserDataType[]
}

const UserAvatar = ({ className, size = 'medium', users }: UserAvatarProps) => {
  const leftSize: number = size == 'medium' ? 20 : 14
  return (
    <div className={`relative flex justify-start image--${size} ${className}`}>
      {users &&
        users.map((user, _index) => (
          <div
            key={user.userId}
            className={`rounded-full avatar-image`}
            style={{
              left: `${_index * leftSize}px`,
              zIndex: users.length - _index,
            }}
          >
            <img
              src={user.profileImage}
              alt={user.username}
              className="relative object-cover rounded-full"
            />
          </div>
        ))}
    </div>
  )
}

export default UserAvatar
