import styles from './Background.module.css'

export default function BackGroundTextBox({ text }: { text: string }) {
  return (
    <div className={styles.container}>
      <h1 className={styles.heading}>{text}</h1>
    </div>
  )
}
