module.exports = {
  '*.(ts|tsx)': [() => 'yarn tsc -p tsconfig.json --noEmit'],
  'packages/ui/**/*.+(ts|tsx)': [
    () => 'yarn tsc -p packages/ui/tsconfig.json --noEmit',
  ],
  'packages/web/**/*.(ts|tsx)': [
    () => 'yarn tsc -p packages/web/tsconfig.json --noEmit',
  ],
  '**/*.(ts|tsx|js|jsx)': ['eslint --fix --cache', 'prettier --write'],
}
