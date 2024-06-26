import Image from 'next/image'

import { calculateAge } from '@/lib/calculateAge'
import { defaultImage } from '@/store/defaultState'

import styles from './swipe.module.css'

export default function FrontCard({
  item,
  MBTIName,
}: {
  item: any
  MBTIName: string | null
}) {
  const gender = item.authProfile.gender ? item.authProfile.gender : ''
  const age = item.authProfile.birth ? calculateAge(item.authProfile.birth) : ''

  return (
    <>
      <Image
        src={item.mainProfileImage ?? defaultImage}
        alt={item.uuid}
        fill
        sizes="(max-width: 768px) 100vw, (max-width: 1200px) 50vw, 33vw"
        placeholder="blur"
        blurDataURL="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAoAAAAKCAYAAACNMs+9AAAAFklEQVR42mN8//HLfwYiAOOoQvoqBABbWyZJf74GZgAAAABJRU5ErkJggg=="
        priority
        className={styles['front-image']}
      />
      <div className={styles.front}>
        <div className={styles['front-title']}>
          <h3 className={styles['front-name']}>
            {item.authProfile.nickname ?? ''}
          </h3>
          <div className={styles.others}>
            {gender && <span>{gender}</span>}
            {age && <span>{age}</span>}
            {MBTIName && <span>{MBTIName}</span>}
          </div>
        </div>
      </div>
    </>
  )
}
