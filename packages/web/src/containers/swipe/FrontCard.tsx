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
  const gender = item.authProfile.gender ? '' : ''
  const age = item.authProfile.birth ? calculateAge(item.authProfile.birth) : ''

  return (
    <>
      <Image
        src={item.mainProfileImage ?? defaultImage}
        alt={item.uuid}
        fill
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
